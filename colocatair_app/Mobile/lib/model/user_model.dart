class UserModel {
  int? id;
  String? nom;
  String? prenom;
  String? email;
  String? dateNaissance;
  int? uid;

  UserModel(
      {this.id,
      this.nom,
      this.prenom,
      this.email,
      this.dateNaissance,
      this.uid});

  UserModel.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nom = json['nom'];
    prenom = json['prenom'];
    email = json['email'];
    dateNaissance = json['dateNaissance'];
    uid = json['uid'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['nom'] = nom;
    data['prenom'] = prenom;
    data['email'] = email;
    data['dateNaissance'] = dateNaissance;
    data['uid'] = uid;
    return data;
  }
}
