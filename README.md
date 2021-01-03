![JavaRedux Logo](https://raw.githubusercontent.com/rupindr/java-redux/main/docs/logo/JavaRedux%20logo%20transparent.png)  
# java-redux

JavaRedux is an implementation of [Redux](https://redux.js.org/) library in Java  

## Quick Start
### Prerequisites
A working Java app with maven or gradle set up  
[Learn more about Maven](https://docs.github.com/articles/configuring-apache-maven-for-use-with-github-package-registry/) or [Gradle](https://docs.github.com/articles/configuring-gradle-for-use-with-github-package-registry/)

### Usage
#### Installation with Maven
1. Add this to `pom.xml` file
    ```
    <dependency>
      <groupId>com.rupindr</groupId>
      <artifactId>java-redux</artifactId>
      <version>1.0</version>
    </dependency> 
    ```
2. Run via command line
    ```
    mvn install 
    ```
    
#### Installation with Gradle 
1. Add following to `build.gradle`
    ```
    implementation "com.rupindr:java-redux:1.0"
    ```
2. Run `./gradlew build` via command line

#### Basic code example
Here is a basic usage example. Each component is explained in detail, further down in this guide.

Create a class representing the state of the application
```$java
class AppState implements State {           // each state class must implement State interface

    public String property1 = "";           // we can make these private...
    public String property2 = "";           // ... and provide getters and setters

    @Override
    public int compareTo(Object o) {        // state class must implement compareTo method
        AppState appState = (AppState) o;
        return (this.property1.equals(appState.property1) && this.property2.equals(appState.property2)) ? 0 : 1;
    }
    
    /* clone method */
}
```

Create a store
```$java
Store<AppState> store = new Store<>(); //       store is a state container
```

Register one or more reducers. Reducer modifies the state based on Actions
```$java
store.registerReducer("main", (action, state) -> {              // Here "main" is the name of the reducer
    AppState newState = state.clone();                          // It is recommended to create new state object
    switch (action.getType()) {
        case "SET_PROP1":                                       // Multiple case blocks modify state according to Action type
            newState.property1 = (String) action.getPayload();  // Never update state properties directly except from within a reducer function
            return newState;
        
        case "SET_PROP2":
            newState.property2 = (String) action.getPayload();
            return newState;
    }
    return state;
}, new AppState());                                             // Third optional parameter is initial state. It is null by default.
```

Subscribe with one or more event handlers on the reducer. All handlers will be called every time the state changes for that specific reducer.
```$java
store.subscribe("main", state -> System.out.println("This is first handler. Property 1 value is " + state.property1));
store.subscribe("main", state -> System.out.println("This is second handler. Property 2 value is " + state.property2));
```

Execute (Dispatch) the actions. There are multiple ways to do this.
```$java
store.dispatch(ActionCreator.create("SET_PROP1", "New value"));
store.dispatch(new Action<String>("SET_PROP2", "New value for prop 2"));
```

Get the state using `store.getState()`, It returns a map `{reducerName -> stateObject}`

## License
Copyright 2021 [Rupinder Singh](https://rupindr.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.