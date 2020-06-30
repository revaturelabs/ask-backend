pipeline {
  agent any
  stages {
    stage('') {
      steps {
        sh '''chmod +x mvnw
./mvnw package
./mvnw install dockerfile:build'''
      }
    }

  }
}