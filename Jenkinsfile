pipeline {
  environment {
    registry = "priya2802/gitfocus_service_kube"
    registryCredential = 'dockerhub_id'
    dockerImage = ''
  }
   agent any
   tools { 
     maven 'maven 3.6.3' 
   }
   stages {
   stage('Deploy to Kubernetes'){
        steps{
         	sh "ssh ubuntu@ec2-13-232-156-182.ap-south-1.compute.amazonaws.com"
            sh 'kubectl apply -f deployment.yml'
       }
    }
   	}
}
