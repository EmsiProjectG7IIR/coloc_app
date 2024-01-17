import 'dart:convert';
import 'dart:developer';
import 'package:modernlogintute/model/offer_model.dart';
import 'package:modernlogintute/config.dart';
import 'package:http/http.dart' as http;
import 'package:modernlogintute/model/offre_list_model.dart';

class OfferService {
  static const _viewUrl = "$apiUrlOffer/offer/all";
  static const _saveUrl = "$apiUrlOffer/offer/save"; 

  static List<OfferListModel> dataFromJson(String jsonString) {
    final data = json.decode(jsonString);
    return List<OfferListModel>.from(data.map((item) => OfferListModel.fromJson(item)));
  }

  static Future<List<OfferListModel>> getData() async {
    try {
      final response = await http.get(Uri.parse(_viewUrl));
      if (response.statusCode == 200) {
        List<OfferListModel> list = dataFromJson(response.body);
        log("message : ${response.body}");
        return list;
      } else {
        log("Failed to fetch data. Status code: ${response.statusCode}");
        return []; // Return an empty list on error
      }
    } catch (error) {
      log("Error fetching data: $error");
      return []; // Return an empty list on error
    }
  }

  static Future<void> saveOffer(OfferModel offer) async {
    try {
      final response = await http.post(
        Uri.parse(_saveUrl),
        headers: <String, String>{
          'Content-Type': 'application/json',
        },
        body: jsonEncode(offer.toJson()),
      );

      if (response.statusCode == 200) {
        log('Offer saved successfully.');
      } else {
        log('Failed to save offer. Status code: ${response.statusCode}');
        throw Exception('Failed to save offer');
      }
    } catch (error) {
      log('Error saving offer: $error');
      throw Exception('Error saving offer');
    }
  }
}
