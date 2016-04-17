var baseURL = "https://github.com/giancosta86/Chronos-IDE/releases/latest"


function onMobileDevice() {
  return window.screen.availWidth < 1000
}


window.onload = function() {
  if (!onMobileDevice()) {
    setupRunWithMoonDeployButton()
    setupdownloadBinaryZipButton()
  }
}


function setupRunWithMoonDeployButton() {
  getLatestMoonDescriptor(
    baseURL,

    "App.moondeploy",

    function(descriptorURL) {
      var runButton = document.getElementById("runWithMoonDeployButton")
      runButton.href = descriptorURL
      runButton.style.display = "block"
    },

    function(statusCode) {
      alert(`Error while retrieving MoonDeploy descriptor. Status code: ${statusCode}`)
    }
  )
}


function setupdownloadBinaryZipButton() {
  getLatestReleaseArtifact(
    baseURL,

    "Chronos-IDE",

    ".zip",

    function(zipURL) {
      var downloadBinaryZipButton = document.getElementById("downloadBinaryZipButton")
      downloadBinaryZipButton.href = zipURL
      downloadBinaryZipButton.style.display = "block"
    },

    function(statusCode) {
      alert(`Error while retrieving the binary zip artifact. Status code: ${statusCode}`)
    }
  )
}
