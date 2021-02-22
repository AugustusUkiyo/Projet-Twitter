import React, { Component } from 'react';
import logo from './logo-UPMC.png';
import './TwisterPage/page_principale.css';

class Logo extends Component{
    constructor(props){
	super(props);
	this.send = this.send.bind(this);
    }

    send(){
	this.props.gbTwisterPage();
    }

    render(){
	return (
	    	<div id="logo" onClick={this.send}>
		<img id="img" src={logo} alt={"logo"}/>
		</div>
	);
    }
}

export default Logo;

