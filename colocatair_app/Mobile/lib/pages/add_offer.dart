import 'dart:convert';
import 'dart:developer';
import 'dart:io';
import 'package:image_picker/image_picker.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_form_builder/flutter_form_builder.dart';
import 'package:modernlogintute/config.dart';
import 'package:modernlogintute/model/offer_model.dart';
import 'package:intl/intl.dart';
import 'package:http/http.dart' as http;
import 'package:modernlogintute/components/toast_message.dart';
import 'package:modernlogintute/service/offer_service.dart';
import 'package:firebase_storage/firebase_storage.dart';

class AddOfferPage extends StatefulWidget {
  const AddOfferPage({super.key});

  @override
  _AddOfferPageState createState() => _AddOfferPageState();
}

class _AddOfferPageState extends State<AddOfferPage> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _dateDebutController = TextEditingController();
  final TextEditingController _dateFinController = TextEditingController();
  final TextEditingController _titreController = TextEditingController();
  final TextEditingController _statusController = TextEditingController();
  final TextEditingController _descController = TextEditingController();
  final TextEditingController _montantController = TextEditingController();
  final TextEditingController _addressController = TextEditingController();
  final TextEditingController _uidController = TextEditingController();
  FirebaseAuth auth = FirebaseAuth.instance;
  // OfferModel offer = OfferModel();
  File? _image;

  @override
  void initState() {
    super.initState();
    User? user = auth.currentUser;
    _statusController.text = "en attend";
    _uidController.text = user!.uid;
  }

  @override
  void dispose() {
    super.dispose();
    _dateFinController.dispose();
    _dateDebutController.dispose();
    _descController.dispose();
    _addressController.dispose();
  }

  Future<void> _pickImage() async {
    final pickedFile =
        await ImagePicker().pickImage(source: ImageSource.gallery);

    if (pickedFile != null) {
      setState(() {
        _image = File(pickedFile.path);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Form(
            key: _formKey,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                TextFormField(
                  decoration: const InputDecoration(labelText: 'Title'),
                  controller: _titreController,
                  // initialValue: "",
                  // onSaved: (value) => offer.titre = value,
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter a title';
                    }
                    return null;
                  },
                ),
                TextFormField(
                  decoration: const InputDecoration(labelText: 'Address'),
                  controller: _addressController,
                  // initialValue: "",
                  // onSaved: (value) => offer.adresse = value,
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter an address';
                    }
                    return null;
                  },
                ),
                TextFormField(
                  decoration: const InputDecoration(labelText: 'Amount'),
                  controller: _montantController,
                  // initialValue: '0.0',
                  keyboardType: TextInputType.number,
                  // onSaved: (value) => offer.montant = double.parse(value!),
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter an amount';
                    }
                    return null;
                  },
                ),
                FormBuilderDateTimePicker(
                  name: 'Date debut',
                  inputType: InputType.both,
                  decoration: const InputDecoration(labelText: 'Debut Date'),
                  initialDate: DateTime.now(),
                  initialValue: DateTime.now(),
                  format: DateFormat('yyyy-MM-dd'),
                  controller: _dateDebutController,
                ),
                FormBuilderDateTimePicker(
                  name: 'Date fin',
                  inputType: InputType.both,
                  decoration: const InputDecoration(labelText: 'Fin Date'),
                  initialDate: DateTime.now(),
                  initialValue: DateTime.now(),
                  format: DateFormat('yyyy-MM-dd'),
                  controller: _dateFinController,
                ),
                TextFormField(
                  decoration: const InputDecoration(labelText: 'Description'),
                  controller: _descController,
                  // initialValue: "",
                  maxLines: 3,
                  // onSaved: (value) => offer.description = value,
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter a description';
                    }
                    return null;
                  },
                ),
                const SizedBox(height: 16.0),
                ElevatedButton(
                  onPressed: _pickImage,
                  style: ElevatedButton.styleFrom(
                    backgroundColor: const Color(0XFF08B783),
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(10.0),
                    ),
                  ),
                  child: const Text('Image',
                      style: TextStyle(color: Colors.white)),
                ),

                // Display selected image
                if (_image != null)
                  Image.file(
                    _image!,
                    height: 100,
                  ),
                const SizedBox(height: 16.0),

                ElevatedButton(
                  onPressed: () {
                    if (_formKey.currentState!.validate()) {
                      _submitForm(
                          _addressController.text,
                          _montantController.text,
                          _titreController.text,
                          _dateFinController.text,
                          _descController.text,
                          _dateDebutController.text,
                          _uidController.text,
                          _statusController.text,
                          _image);
                    }
                  },
                  child: const Text('Add Offer'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  _submitForm(
      adress, montant, titre, dateF, desc, dateD, uid, status, image) async {
    if (_formKey.currentState!.validate()) {
      String photoUrl = await _uploadImage(image);
      log("=====================>>>dddd $titre");

      final offer = OfferModel(
        idCreateur: uid,
        titre: titre,
        dateDebut: dateD,
        dateFin: dateF,
        description: desc,
        adresse: adress,
        montant: montant,
        status: status,
        photo: photoUrl,
      );

      try {
        await OfferService.saveOffer(offer);
        ToastMsg.showToastMsg("Offer saved successfully!");
        Navigator.pop(context);
      } catch (e) {
        ToastMsg.showToastMsg("Smoothing went wrong");
      }
    }
  }

  Future<String> _uploadImage(File? image) async {
    if (image == null) {
      return Future.error("Image is null");
    }

    try {
      String fileName = "image_${DateTime.now().millisecondsSinceEpoch}.jpg";

      Reference storageReference =
          FirebaseStorage.instance.ref().child("images/$fileName");

      await storageReference.putFile(image);
      log("g11111");

      String downloadURL = await storageReference.getDownloadURL();
      return downloadURL;
    } catch (error) {
      ToastMsg.showToastMsg("Error uploading image: $error");
      return Future.error("Failed to upload image");
    }
  }
}
