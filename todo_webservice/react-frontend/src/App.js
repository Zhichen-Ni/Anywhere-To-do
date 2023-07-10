import AuthorizationService from './services/AuthorizationService';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import MainComponent from './components/MainComponent';
import HeaderComponent from './components/HeaderComponent';
import ReactDOM from 'react-dom/client';
import * as React from 'react';
import PlanComponent from "./components/PlanComponent";
import TodayComponent from "./components/TodayComponent";




function App() {



  return (
        <div>
          <Router>
            <div>
              {/*<HeaderComponent />*/}
              <div>
                <Routes>
                    <Route path='/' element={<HeaderComponent myComponent={<MainComponent/>}/>} />
                    <Route path='/home' element={<HeaderComponent myComponent={<MainComponent/>}/>} />
                    <Route path='/today' element={<HeaderComponent myComponent={<TodayComponent/>}/>} />
                    <Route path='/planned' element={<HeaderComponent myComponent={<PlanComponent/>}/>} />
                </Routes>
              </div>
            </div>
          </Router>
        </div>
  );
}

export default App;
