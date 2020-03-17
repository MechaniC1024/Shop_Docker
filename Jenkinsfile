pipeline {
  agent any
  options {
      buildDiscarder (logRotator (numToKeepStr: '5'))
  }
  stages{
    stage("TEST"){
      steps{
        git url: "https://github.com/MechaniC1024/Shop_Docker.git"
        dir("PrestaShop"){
          bat ''' 
              IF NOT EXIST logs mkdir logs
              IF NOT EXIST video mkdir video
              '''
          powershell '''$current = $PWD -replace "\\\\", "/" -replace "C", "c"
                        ${current}
                        $current_video = $PWD -replace "\\\\", "/" -replace "C:", "/c"
                        ${current_video}
                        docker run -d --name selenoid -p 4444:4444 -v //var/run/docker.sock:/var/run/docker.sock -v ${current}/SelenoidConfig/:/etc/selenoid/:ro -v ${current}/logs/:/opt/selenoid/logs/ -v ${current_video}/video/:/opt/selenoid/video/ -e OVERRIDE_VIDEO_OUTPUT_DIR=${current_video}/video/ --rm aerokube/selenoid:latest-release -limit 10 -log-output-dir /opt/selenoid/logs
                     '''
          bat 'docker run -d --name selenoid-ui --link selenoid -p 8090:8090 --rm aerokube/selenoid-ui --selenoid-uri=http://selenoid:4444 --listen 0.0.0.0:8090'
          powershell'''   
                               $selenoid_id = docker ps -qf "name=selenoid$"
                               $selenoid_ui_id = docker ps -qf "name=selenoid-ui$"
                               "SELENOID_ID=$selenoid_id `nSELENOID_UI_ID=$selenoid_ui_id" | Out-File SELENOID.properties -Encoding ASCII
                              '''
                              catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                                bat ''' mvn clean test '''
                            }
         
          
        }
      }
    }
            stage("REMOVE DOCKER CONTAINERS"){
            steps{
                dir("PrestaShop"){
                powershell '''
                          sleep 5
                          $Props = convertfrom-stringdata (get-content ./SELENOID.properties -raw)
                          docker stop $Props.SELENOID_ID
                          docker stop $Props.SELENOID_UI_ID
                          Remove-Item SELENOID.properties
                          '''
                }
                bat '''
                    cd.. 
                    move workspace/PrestaShop/video builds/%BUILD_NUMBER%
                    move workspace/PrestaShop/logs builds/%BUILD_NUMBER%
                    '''
            }
        }
        stage("REPORT"){
        steps{
            allure ([includeProperties: true, 
                  reportBuildPolicy: 'ALWAYS',
                  report: 'target/allure-report', 
                  results: [[path: 'PrestaShop/target/allure-results']]
            ])
        }
    }
  }
} 