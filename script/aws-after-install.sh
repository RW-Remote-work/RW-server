#!/bin/bash
set -xe

# Copy war file from S3 bucket to tomcat webapp folder
aws s3 cp s3://rw-public-github-action-deploy-store/rwws-admin.jar /opt/rwws/rwws-admin.jar

echo "-----copy jar ok-----" >> start.log