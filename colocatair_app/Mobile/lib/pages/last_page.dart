import 'package:flutter/material.dart';

class LastPage extends StatelessWidget {
  const LastPage({Key? key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Container(
        decoration: const BoxDecoration(
          image: DecorationImage(
            image: AssetImage('lib/images/gradient4.jpg'),
            fit: BoxFit.cover,
          ),
        ),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(
                onPressed: () {
                  Navigator.pop(context);
                },
                style: ElevatedButton.styleFrom(
                  primary: Colors.white, 
                  onPrimary: Colors.black, 
                ),
                child: const Padding(
                  padding: EdgeInsets.all(15.0),
                  child: Text('SEND MESSAGE'),
                ),
              ),
              const SizedBox(height: 25),
              ElevatedButton(
                onPressed: () {
                  Navigator.pop(context);
                },
                style: ElevatedButton.styleFrom(
                  primary: Color.fromARGB(255, 242, 126, 149), 
                  onPrimary: Colors.white, 
                ),
                child: const Padding(
                  padding: EdgeInsets.all(15.0),
                  child: Text('KEEP SWIPING'),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
