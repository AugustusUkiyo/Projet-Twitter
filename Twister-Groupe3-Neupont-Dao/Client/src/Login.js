import React, { Component } from 'react';
import axios from 'axios';
import './connexion.css';

class Login extends Component{
    constructor(props){ 
	super(props);
	this.state = {status : "" , msg_error : ""};
	this.send = this.send.bind(this)
	this.reponse_login = this.reponse_login.bind(this);
    }

    reponse_login(reponse){
	if(reponse.data["status"] == "Error"){
	    this.setState({status : "error", msg_error : reponse.data["Erreur"]});
	}
	else{
	    this.setState({status : "", msg_error : ""});
	    this.props.login(reponse.data["key"],reponse.data["id"]);
	}
    }

    send(){
	const formData = new URLSearchParams();
	formData.append("login",this.refs.login.value);
	formData.append("password",this.refs.password.value);
	axios.get("http://localhost:8080/TwisterGN&DH/User/Login?"+formData).then(reponse => {this.reponse_login(reponse);});
    }
    
    render(){
	return (
		<html>
		<head>
		<title> Connexion </title>
		</head>
		<body>
		<h1 id="h1c">Ouvrir une session</h1>
		<div id="formc">
		<div id="t1">
		
		<div id="t2">
		<span id="sl">Login</span>
		<input type="text" name="login" id="login" ref="login"/>
		</div>
		
		<div id="t3">
		<span id="sp">Password</span>
		<input type="password" name="password" id="password" ref="password"/>
		</div>
		
		<div id="t4">
		<div id="t8">
		<button id="sub" className='button' onClick={this.send}> Connexion </button>
		</div>
		<div id="t5">
		<div id="t6">
		<a href="#">Mot de passe perdu</a>
		</div>
		<div id="t7">
		<a href="#" onClick={this.props.sign}> Pas encore inscrit ?</a>
		</div>
		</div>
		</div>
		</div>
		</div>
		<div id="msg_error_co">
		{this.state.status === "error" ? <p id="p"> Erreur lors de la connexion : {this.state.msg_error}</p> : ""}
	    </div>
		</body>
		</html>

	);
    }
}

export default Login;
