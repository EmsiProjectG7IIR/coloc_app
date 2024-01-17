import 'dart:convert';
import 'dart:developer';
import 'package:modernlogintute/model/offer_model.dart';
import 'package:modernlogintute/config.dart';
import 'package:http/http.dart' as http;

class OfferService {
  static const _viewUrl = "$apiUrlOffer/all";
  static const _saveUrl = "$apiUrlOffer/save";

  static List<OfferModel> dataFromJson(String jsonString) {
    final data = json.decode(jsonString);
    return List<OfferModel>.from(data.map((item) => OfferModel.fromJson(item)));
  }

  static Future<List<OfferModel>> getData() async {
    try {
      final response = await http.get(Uri.parse(_viewUrl));
      if (response.statusCode == 200) {
        List<OfferModel> list = dataFromJson(response.body);
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
    log("message mmm ${offer.toJson()}");
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
