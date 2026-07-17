# JVM Bootstrapping & Custom ClassLoading Demonstration

A hands-on exploration of the Java Virtual Machine's internal architecture, proving the Parent Delegation Model and demonstrating runtime dynamic bytecode injection.

## Features Explored
* **The Bootstrap ClassLoader:** Inspected the native root engine loader.
* **The Parent Delegation Chain:** Traced how class-loading requests bubble up to prevent security overrides.
* **Custom Dynamic Loading:** Built a custom ClassLoader using `defineClass()` to fetch and instantiate invisible modules at runtime via reflection.

## How to Run Locally
1. Clone this repository.
2. Compile the external target module into the hidden directory.
3. Run `java src.CustomBootstrapLoader` to execute the custom verification sequence.
