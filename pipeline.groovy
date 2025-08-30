pipeline {
    agent any 
    stages {
        stage('code-pull'){
            steps {
                git branch: 'dev', url: 'https://github.com/ankitd1997/project-backend.git'
            }
        }
        stage('code-Build'){
            steps {
               sh 'mvn clean package'
            }
        }
         stage('Deploy-K8s'){
            steps {
               sh '''
                    docker build . -t ankit00398/project-backend-img:latest
                    docker push ankit00398/project-backend-img:latest
                    docker rmi ankit00398/project-backend-img:latest
                    kubectl apply -f ./deploy/

               '''
            }
        }
    }
}
