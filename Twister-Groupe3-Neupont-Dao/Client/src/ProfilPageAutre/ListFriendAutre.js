import React, { Component } from 'react';
import axios from 'axios';
import './page_profil_autre.css';

class ListFriendAutre extends Component{
    constructor(props){
	super(props);
	this.state = {list_friend : []}
	this.maj_liste = this.maj_liste.bind(this);
	this.chargeProfil = this.chargeProfil.bind(this);
    }
    
    maj_liste(){
	const formData = new URLSearchParams();
	formData.append("key",this.props.cle);
	formData.append("id",this.props.id_pro);
	axios.get('http://localhost:8080/TwisterGN&DH/Friend/ListFriends?'+formData).then(reponse =>
											  {
											      if(reponse.data["status"] === ""){
												  const posts =reponse.data["amis"].slice(0,10);
												  this.setState({list_friend : posts});
											      }
											      else{
												  alert(reponse.data["Erreur"]);
											      }
											  });
    }
    
    componentDidMount(){
	this.maj_liste();
    }

    chargeProfil(id){
	this.props.profil(id)
    }

    render(){
	return (
		<div id="liste_ami_pro_autre">
		<span id="titre_liste_ami_pro_autre">Liste d amis :</span><br></br>
		<ul>
		{
		    this.state.list_friend.map(
			(per,index) =>
			    (
				    <div>
				    <li>
				    <a href="#" onClick={() => {this.chargeProfil(per["id"])}}>{per["prenom"]} {per["nom"]}</a>
				    </li>
				    </div>
				    
			    )
		    )
		}
	    </ul>
		</div> 	    
	);
    }
}

export default ListFriendAutre;
