import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import { Theme as UWPThemeProvider, getTheme } from "react-uwp/Theme";

ReactDOM.render(

	<UWPThemeProvider
	theme={getTheme({
		themeName: "dark", // set custom theme
		accent: "#0078D7", // set accent color
		useFluentDesign: true, // sure you want use new fluent design.
		desktopBackgroundImage: "http://127.0.0.1:8092/static/images/jennifer-bailey-10753.jpg" // set global desktop background image
	})}
	>
	<App />
	</UWPThemeProvider>
	, document.getElementById('root')
);
registerServiceWorker();
