pipeline {
  environment {
    registry = "priya2802/gitfocus_service_kube"
    registryCredential = 'dockerhub_credentials'
    dockerImage = ''
  }
    agent any
   stages {
   stage('Deploy to Kubernetes'){
        steps{
         	sh "ssh -o StrictHostKeyChecking=no user ubuntu@172.31.35.76"
            sh 'kubectl apply -f deployment.yml'
       }
    }
   	}
}
