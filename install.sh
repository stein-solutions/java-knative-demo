#!/bin/bash

helm upgrade --install java-knative-demo kubernetes/helm/java-knative-demo -n java-knative-demo
echo "installed helm chart to k8s"