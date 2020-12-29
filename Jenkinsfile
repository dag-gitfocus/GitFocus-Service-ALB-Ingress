pipeline {

  agent any 

    stage('Deploy App to Kubernetes') {
      steps {
        script {
          kubernetesDeploy(configs: "nginx.yaml", kubeconfigId: "mykubeconfig")
        }
      }
    }

  }
}