class OfferModel {
  int? id;
  String? idCreateur;
  String? titre;
  String? dateDebut;
  String? dateFin;
  String? description;
  String? adresse;
  double? montant;
  String? status;
  String? photo;

  OfferModel(
      {this.id,
      this.idCreateur,
      this.titre,
      this.dateDebut,
      this.dateFin,
      this.description,
      this.adresse,
      this.montant,
      this.status,
      this.photo});

  OfferModel.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    idCreateur = json['id_createur'];
    titre = json['titre'];
    dateDebut = json['dateDebut'];
    dateFin = json['dateFin'];
    description = json['description'];
    adresse = json['adresse'];
    montant = json['montant'];
    status = json['status'];
    photo = json['photo'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['id_createur'] = idCreateur;
    data['titre'] = titre;
    data['dateDebut'] = dateDebut;
    data['dateFin'] = dateFin;
    data['description'] = description;
    data['adresse'] = adresse;
    data['montant'] = montant;
    data['status'] = status;
    data['photo'] = photo;
    return data;
  }
}
