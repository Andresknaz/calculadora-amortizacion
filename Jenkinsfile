pipeline {
    agent any
    
    
    triggers {
        cron('* * * * *') 
    }
    
    tools {
        
        maven 'Maven' 
    }

    stages {
       
        stage('Checkout') {
            steps {
               
                checkout scm
            }
        }
        
        
        stage('Análisis SonarQube') {
            steps {
                
                withSonarQubeEnv('SonarQube') {
                    
                    sh 'mvn clean verify sonar:sonar -DskipTests'
                }
            }
        }
        
        
        stage('Validar Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    
                    waitForQualityGate abortPipeline: true 
                }
            }
        }
        
        
        stage('Preparar Producción') {
            steps {
                sh 'mvn package -DskipTests'
                echo "Éxito: El código pasó las pruebas de calidad y ha sido empaquetado para producción."
            }
        }
    }
    
    
    post {
        failure {
            
            mail to: 'tu-correo@dominio.com',
                 subject: "Fallo en el pipeline: ${env.JOB_NAME} [Build #${env.BUILD_NUMBER}]",
                 body: "Atención: El código subido a GitHub no ha superado la regla de SonarQube o hubo un error en la compilación. Revisa la consola en Blue Ocean."
        }
        success {
            echo "Pipeline completado con éxito. Todo el código cumple con la calidad requerida."
        }
    }
}