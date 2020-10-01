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
         	sh "ec2-15-206-69-254.ap-south-1.compute.amazonaws.com"
            sh 'kubectl apply -f deployment.yml'
       }
    }
   	}
}
