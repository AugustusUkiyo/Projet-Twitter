import React, { Component } from 'react';
import axios from 'axios';
import './page_search.css';

class Search extends Component{
    constructor(props){
	super(props);
	this.state = {list : []}
	this.chargeProfil = this.chargeProfil.bind(this);
    }
    
    
    componentDidMount(){
		const formData = new URLSearchParams();
		formData.append("key",this.props.cle);
		formData.append("texte",this.props.text_search);
		axios.get('http://localhost:8080/TwisterGN&DH/Search/Search?'+formData).then(reponse =>
												  {
												      if(reponse.data["status"] === ""){
														  const posts =reponse.data["res"].slice(0,20);
														  this.setState({list : posts});
												      }
												      else{
													  	  alert(reponse.data["Erreur"]);
												      }
												  });
    }

    chargeProfil(id){
		this.props.profil(id);
    } 

    render(){
	return (
		<div id="liste_search">
		<span id="titre_liste_search">Liste des résultats :</span><br></br>
		<lo>
		{
		    this.state.list.map(
			(per,index) =>
			    (
				    <li>
				    <a href="#" onClick={() => {this.chargeProfil(per["id"])}}>{per["prenom"]} {per["nom"]}</a>
				    </li>
			    )
		    )
		}
		</lo>
		</div> 	    
	);
    }
}

export default Search;
