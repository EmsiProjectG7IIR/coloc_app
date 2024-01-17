import 'dart:convert';
import 'dart:developer';

import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_form_builder/flutter_form_builder.dart';
import 'package:modernlogintute/config.dart';
import 'package:modernlogintute/model/offer_model.dart';
import 'package:intl/intl.dart';
import 'package:http/http.dart' as http;
import 'package:modernlogintute/model/user_model.dart';
import 'package:modernlogintute/pages/toast_message.dart';
import 'package:modernlogintute/service/offer_service.dart';
import 'package:modernlogintute/service/user_service.dart';
// import 'package:shared_preferences/shared_preferences.dart';

class AddOfferPage extends StatefulWidget {
  const AddOfferPage({super.key});

  @override
  _AddOfferPageState createState() => _AddOfferPageState();
}

class _AddOfferPageState extends State<AddOfferPage> {
  final TextEditingController _dateController = TextEditingController();
  final TextEditingController _timeController = TextEditingController();
  final TextEditingController _appointmentTypeController =
      TextEditingController();
  final TextEditingController _statusController = TextEditingController();
  final TextEditingController _serviceTimeController = TextEditingController();
  final TextEditingController _appointmentIdController =
      TextEditingController();
  final TextEditingController _uIdController = TextEditingController();
  final TextEditingController _descController = TextEditingController();
  final TextEditingController _createdDateTimeController =
      TextEditingController();
  final TextEditingController _lastUpdatedController = TextEditingController();

  final OfferModel _offer = OfferModel();

  @override
  void initState() {
    super.initState();
    _offer.dateCreation = DateTime.now();
    _offer.status = "en attend";
    _offer.photo = "test";
    _findByData();
  }

  @override
  void dispose() {
    super.dispose();
    _dateController.dispose();
    _timeController.dispose();
    _appointmentTypeController.dispose();
    _serviceTimeController.dispose();
    _appointmentIdController.dispose();
    _uIdController.dispose();
    _descController.dispose();
  }

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

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
                  initialValue: 'TEST Offer',
                  onSaved: (value) => _offer.titre = value,
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter a title';
                    }
                    return null;
                  },
                ),

                TextFormField(
                  decoration: const InputDecoration(labelText: 'Address'),
                  initialValue: 'test',
                  onSaved: (value) => _offer.adresse = value,
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter an address';
                    }
                    return null;
                  },
                ),

                TextFormField(
                  decoration: const InputDecoration(labelText: 'Amount'),
                  initialValue: '100.0',
                  keyboardType: TextInputType.number,
                  onSaved: (value) => _offer.montant = double.parse(value!),
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter an amount';
                    }
                    return null;
                  },
                ),

                // FormBuilderTextField(
                //   name: 'id_createur',
                //   decoration: const InputDecoration(labelText: 'Creator ID'),
                //   keyboardType: TextInputType.number,
                //   onSaved: (value) {
                //     _offer.idCreateur = int.parse(value!);
                //   },
                // ),

                // FormBuilderTextField(
                //   name: 'photo',
                //   decoration: const InputDecoration(labelText: 'Photo URL'),
                //   onSaved: (value) {
                //     _offer.photo = value!;
                //   },
                // ),
                //  FormBuilderCheckbox(
                //     name: 'status',
                //     initialValue: false, // Set default value as needed
                //     title: Text('Status'),
                //     // onChanged: (value) {
                //     //   _offer.status = value;
                //     // },
                //   ),

                // FormBuilderDateTimePicker(
                //   name: 'dateCreation',
                //   inputType: InputType.both,
                //   decoration: const InputDecoration(labelText: 'Creation Date'),
                //   initialDate: DateTime.now(),
                //   initialValue: DateTime.now(),
                //   format: DateFormat('yyyy-MM-dd HH:mm'),
                //   onChanged: (value) {
                //     // Handle onChanged if needed
                //   },
                //   onSaved: (value) {
                //     _offer.dateCreation = value;
                //   },
                // ),

                FormBuilderDateTimePicker(
                  name: 'Date debut',
                  inputType: InputType.both,
                  decoration: const InputDecoration(labelText: 'Debut Date'),
                  initialDate: DateTime.now(),
                  initialValue: DateTime.now(),
                  format: DateFormat('yyyy-MM-dd HH:mm'),
                  onChanged: (value) {
                    // Handle onChanged if needed
                  },
                  onSaved: (value) {
                    _offer.dateDebut = value;
                  },
                ),

                FormBuilderDateTimePicker(
                  name: 'Date fin',
                  inputType: InputType.both,
                  decoration: const InputDecoration(labelText: 'Fin Date'),
                  initialDate: DateTime.now(),
                  initialValue: DateTime.now(),
                  format: DateFormat('yyyy-MM-dd HH:mm'),
                  onChanged: (value) {
                    // Handle onChanged if needed
                  },
                  onSaved: (value) {
                    _offer.dateFin = value;
                  },
                ),

                TextFormField(
                  decoration: const InputDecoration(labelText: 'Description'),
                  initialValue: 'This is a sample _offer description.',
                  maxLines: 4,
                  onSaved: (value) => _offer.description = value,
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter a description';
                    }
                    return null;
                  },
                ),
                const SizedBox(height: 16.0),

                ElevatedButton(
                  onPressed: () {
                    _submitForm(_offer);
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

  _findByData() async {
    try {
      FirebaseAuth auth = FirebaseAuth.instance;
      User? user = auth.currentUser;

      if (user != null) {
        String uid = user.uid;

        UserModel? data = await UserService.getDataByUid(uid);
        log("eeeeeeeeeeeeeeeeeeeee  ${user.uid}");

        log("Idd : ${data?.id}");
        _offer.idCreateur = data?.id;
      } else {
        ToastMsg.showToastMsg("Smoothing went wrong");
      }
    } catch (e) {
      ToastMsg.showToastMsg("Smoothing went wrong");
    }
  }

  _submitForm(offer) async {
    if (_formKey.currentState!.validate()) {
      // _formKey.currentState!.save();
      log("merr ");
      try {
        await OfferService.saveOffer(offer);
        ToastMsg.showToastMsg("Offer saved successfully!");
        Navigator.pop(context);
      } catch (e) {
        ToastMsg.showToastMsg("Smoothing went wrong");
      }
    }
  }
}
