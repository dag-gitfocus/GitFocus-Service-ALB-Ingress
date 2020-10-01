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
         	sh "ssh -o StrictHostKeyChecking=no ubuntu@15.206.69.254"
            sh 'kubectl apply -f deployment.yml'
       }
    }
   	}
}
