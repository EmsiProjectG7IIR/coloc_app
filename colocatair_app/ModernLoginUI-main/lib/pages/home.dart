import 'package:flutter/material.dart';

class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CardCarousel(),
    );
  }
}

class CardCarousel extends StatefulWidget {
  @override
  _CardCarouselState createState() => _CardCarouselState();
}

class _CardCarouselState extends State<CardCarousel> {
  final List<String> offers = ['Special Offer 1', 'Special Offer 2', 'Special Offer 3'];
  int currentIndex = 0;

  void _handleAccept() {
    print('Accepted: ${offers[currentIndex]}');
    _moveToNextCard();
  }

  void _handleRefuse() {
    print('Refused: ${offers[currentIndex]}');
    _moveToNextCard();
  }

  void _moveToNextCard() {
    setState(() {
      currentIndex = (currentIndex + 1).clamp(0, offers.length - 1);
    });
  }

  void _moveToPreviousCard() {
    setState(() {
      currentIndex = (currentIndex - 1).clamp(0, offers.length - 1);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Container(
          height: 500.0, // Adjust the height as needed
          child: Card(
            margin: EdgeInsets.all(16.0),
            color: Colors.lightBlue[100], // Set light blue background color
            child: Column(
              children: [
                ListTile(
                  title: Text(offers[currentIndex]),
                ),
                ButtonBar(
                  alignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                      onPressed: _handleAccept,
                      child: Text('Accept'),
                    ),
                    ElevatedButton(
                      onPressed: _handleRefuse,
                      child: Text('Refuse'),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
        SizedBox(height: 16.0),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: _moveToPreviousCard,
              child: Text('Previous'),
              style: ElevatedButton.styleFrom(
                primary: Colors.orange, 
              ),
            ),
            SizedBox(width: 16.0),
            ElevatedButton(
              onPressed: _moveToNextCard,
              child: Text('Next'),
              style: ElevatedButton.styleFrom(
                primary: Colors.orange, 
              ),
            ),
          ],
        ),
      ],
    );
  }
}
