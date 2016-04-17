/*§
  ===========================================================================
  MoonDeploy - JS
  ===========================================================================
  Copyright (C) 2016 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

function getLatestMoonDescriptor(baseURL, descriptorFileName, successCallback, errorCallback) {
  if (!descriptorFileName.endsWith(".moondeploy")) {
    throw "The descriptor file name must end with '.moondeploy'"
  }

  getLatestReleaseArtifact(baseURL, descriptorFileName, "", successCallback, errorCallback)
}


function getLatestReleaseArtifact(baseURL, fileNamePrefix, fileNameSuffix, successCallback, errorCallback) {
  scanAssetsInGitHubLatestRelease(
    baseURL,

    function(responseObject, asset) {
      if (asset.name.startsWith(fileNamePrefix) && asset.name.endsWith(fileNameSuffix)) {
        successCallback(asset.browser_download_url)
        return true
      }

      return false
    },


    function(responseObject) {
      throw `Cannot find latest release artifact filename starting with '${fileNamePrefix}' and ending with '${fileNameSuffix}' at '${responseObject.html_url}'`
    },

    errorCallback
  )
}


function scanAssetsInGitHubLatestRelease(baseURL, assetFunction, allAssetsScannedFunction, errorCallback) {
  var githubLatestReleaseRegex = /^https:\/\/github\.com\/([^/]+)\/([^/]+)\/releases\/latest\/?$/

  var githubLatestComponents = githubLatestReleaseRegex.exec(baseURL)

  if (!githubLatestComponents) {
    throw("The base URL must be a valid GitHub URL ending with /releases/latest")
  }

  var gitHubUser = githubLatestComponents[1]
  var gitHubRepo = githubLatestComponents[2]

  var apiRequest = new XMLHttpRequest()

  apiRequest.onreadystatechange = function() {
    if (apiRequest.readyState == 4) {
      if (apiRequest.status == 200) {
        var responseObject = JSON.parse(apiRequest.responseText)

        for (asset of responseObject.assets) {
          if (assetFunction(responseObject, asset)) {
            return
          }
        }

        allAssetsScannedFunction(responseObject)
      } else {
        if (errorCallback) {
          errorCallback(apiRequest)
        }
      }
    }
  }

  var apiURL = "https://api.github.com/repos/" + gitHubUser + "/" + gitHubRepo + "/releases/latest"

  apiRequest.open("GET", apiURL, true)
  apiRequest.send()
}
