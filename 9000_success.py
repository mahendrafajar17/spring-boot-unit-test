'''
    Simulation!!!

    The Graph API sends error response with complete variables.
'''
from flask import Flask, jsonify, make_response

app = Flask(__name__)

redeem_response = {
    "response": {
        "status": "0",
        "message": "Last transaction success"
    },
    "action": "redeem",
    "balance": 99999
}

@app.route('/redeem', methods=['POST'])
def message():
    response = make_response(jsonify(redeem_response), 200)
    return response
    

if __name__ == '__main__':
    app.run(port=9000, threaded=True)
