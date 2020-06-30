pipeline {
  agent any
  stages {
    stage('DockerizeRun') {
      steps {
        sh '''chmod +x mvnw
./mvnw package
./mvnw install dockerfile:build'''
        sh '''if docker ps -a | grep cranky_beaver; then docker kill cranky_beaver; else echo \'Container not found\'; fi
if docker ps -a | grep cranky_beaver; then docker rm cranky_beaver; else echo \'Container not found\'; fi
docker run -p 1337:1337 --name cranky_beaver -d ask:latest'''
      }
    }

  }
}