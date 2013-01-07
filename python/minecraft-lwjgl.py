##############################################################
# minecraft-lwjgl.py
#
# This python script will grab the latest version of LWJGL 
# from sourceforge and will replace the the minecraft 
# provided version. 
#
# For successful execution, this script requires the 
# python-notify module.
#
# Tested in Ubuntu 12.10 only.
##############################################################

#!/usr/bin/python
import sys, re, os
import pynotify
import urllib2
import zipfile, tarfile
import datetime
import shutil

# Global for base URL
# Function to grab the latest version of LWJGL with some noobish RE coding.
def getLatestVersion():
    url = urllib2.urlopen ("http://sourceforge.net/projects/java-game-lib/files/Official%20Releases/").read()
  
    # The lazy RE way to do this, will match the first link (the most recent source).
    result = re.search('LWJGL [0-9.]*', url)

    # Clean up the result for string concatenation... we know what to expect in terms of linkage.
    clean_result = result.group(0).replace(" ", "%20")
    
    return clean_result

# Function to grab the source and place it next to this file (we will just clean it up when we are done).
def getSource(latestVersion):
    source_name = (latestVersion.replace("%20","-")+".zip").lower()
    download_url = "http://sourceforge.net/projects/java-game-lib/files/Official%20Releases/" + latestVersion + "/" + source_name + "/download"

    # Download the source.
    request = urllib2.urlopen(download_url)
    
    # Create an output file and write downloaded zip contents there.
    output = open(source_name, "w")
    output.write(request.read())
    output.close()

# Function to cleanup the User's minecraft config directory.
def cleanupUserMinecraft(minecraft_dir, current_dir, latestVersion):
    # I am going to leave the residual zip when we are done, though we could clean it up.
    source_name = (latestVersion.replace("%20","-")+".zip").lower()
    
    # Unzip our stable source. It will have the latestVersion as its dir name.
    with zipfile.ZipFile(source_name,"r") as zip_handler:
        zip_handler.extractall("./")
    
    # Create a tar backup of ./minecraft/bin. Append a date/time stamp to alleviate overwrites 
    # due to multiple successive execution of this script (some people are double-click happy).
    date_stamp = str(datetime.datetime.now())
    tar = tarfile.open(minecraft_dir+"/bin_backup"+date_stamp+".tar", "w")
    tar.add(minecraft_dir+"/bin")
    tar.close()

    # Now lets move the pertinent files into their respective directories.
    src_lwjgl_dir_jar = "./"+source_name.replace(".zip","")+"/jar/"
    src_lwjgl_dir_native = "./"+source_name.replace(".zip","")+"/native/linux"
    dst_minecraft_dir = minecraft_dir+"/bin"
    listFiles = os.listdir(src_lwjgl_dir_jar)
    
    # For files we care about in the directory, use shutil to copy the files over. We use
    # copy as it overwrites existing files of the same name.
    shutil.copy(src_lwjgl_dir_jar+"/jinput.jar", dst_minecraft_dir)
    shutil.copy(src_lwjgl_dir_jar+"/lwjgl.jar", dst_minecraft_dir)
    shutil.copy(src_lwjgl_dir_jar+"/lwjgl_util.jar", dst_minecraft_dir)
    shutil.copy(src_lwjgl_dir_native+"/libjinput-linux.so", dst_minecraft_dir+"/natives/")
    shutil.copy(src_lwjgl_dir_native+"/libjinput-linux64.so", dst_minecraft_dir+"/natives/")
    shutil.copy(src_lwjgl_dir_native+"/liblwjgl.so", dst_minecraft_dir+"/natives/")
    shutil.copy(src_lwjgl_dir_native+"/liblwjgl64.so", dst_minecraft_dir+"/natives/")
    shutil.copy(src_lwjgl_dir_native+"/libopenal.so", dst_minecraft_dir+"/natives/")
    shutil.copy(src_lwjgl_dir_native+"/libopenal64.so", dst_minecraft_dir+"/natives/")          

# Function to manage Ubuntu-based notifications. It has an instant message image at the moment,
# this is because I couldn't readily find the API. I could read the source I suppose... that
# will come later. Promise!
def notify(status, msg=None):
    if status is True:
        # If this is true then we must have finished cleanly.
        n = pynotify.Notification(
            "Minecraft LWJGL Update",
            "You have updated the Minecraft LWJGL version to the latest available stable release.",
            "notification-message-im")
        n.show()
    elif status is False:
        # If this is false we must have errored somewhere, report the error to notification and
        # exit.
        if not msg is None:
            n = pynotify.Notification(
                "Minecraft LWJGL Failed!",
                msg,
                "notification-message-im")
            n.show()
            sys.exit(1)
        else:
            n = pynotify.Notification(
                "Minecraft LWJGL Failed!",
                "No error message was provided - lazy developer!",
                "notification-message-im")
            n.show()
            sys.exit(1)

# Our very last function to do some clean-up because we have a lot of files!
def cleanAll(latestVersion, curr_dir):
    source_dir = (latestVersion.replace("%20","-")).lower()
    shutil.rmtree(curr_dir+"/"+source_dir)
    
    # Remove the zip too. Up to you to uncomment this or not.
    os.remove(curr_dir+"/"+source_dir+".zip")

# Our main for configuring our application execution path.
def main(argv=None):
    if not pynotify.init("icon-summary-body"):
        sys.exit(1)

    # Ensure that the .minecraft directory exists, if not error out.
    minecraft_dir = os.path.expanduser("~")+"/.minecraft"
    if not os.path.isdir(minecraft_dir):
        notify(False, "You do not have a .minecraft directory in your home directory. Installation halted.")

    # Reveal the latest version of the LWJGL.
    latestVersion = getLatestVersion()

    # Download the latest version of LWJGL... if it isn't in the current working directory already.
    if not os.path.isdir(os.getcwd()+"./"+(latestVersion.replace("%20","-")+".zip").lower()):
        getSource(latestVersion)

    # Backup old LWJGL files and place the new files.
    cleanupUserMinecraft(minecraft_dir, os.getcwd(), latestVersion)

    # Clean-up the downloaded file and notify the user of the changes.
    cleanAll(latestVersion, os.getcwd())

    # Notify fuction. Notice it takes a status input, as we could alert the user if 
    # something failed. I'm too lazy to do at that moment.
    notify(True)

if __name__ == "__main__":
    sys.exit(main())

