CAREEM_LOCAL_MAVEN_PATH=~/.m2/repository/com/careem/
ARTIFACT_PATH=$(pwd)/artifacts/mockingbird.zip
mkdir -p $CAREEM_LOCAL_MAVEN_PATH &&
  unzip $ARTIFACT_PATH -d $CAREEM_LOCAL_MAVEN_PATH
