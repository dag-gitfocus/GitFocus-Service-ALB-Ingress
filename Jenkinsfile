pipeline {

  agent any 

  stages {

    stage('Checkout Source') {
      steps {
        git url:'https://github.com/dag-gitfocus/GitFocus_Service.git'
      }
    }

    stage('Deploy App') {
      steps {
        script {
          kubernetesDeploy(configs: "deployment.yaml", kubeconfigId: "mykubeconfig")
        }
      }
    }

  }

}