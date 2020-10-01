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
         	sh "ssh ubuntu@ec2-13-232-156-182.ap-south-1.compute.amazonaws.com"
            sh 'kubectl apply -f deployment.yml'
       }
    }
   	}
}
