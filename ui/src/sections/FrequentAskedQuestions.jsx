import React from 'react'

import '../css/FrequentAskedQuestions.css'

function FrequentAskedQuestions({ title, items }) {
    return (
        <div id="faq" className="faq-body">
            <div className="faq-header">
                <h3 className="faq-title">{title}</h3>
                <div className="seperator"></div>
            </div>
            <div className="faq-list">
                {
                    items.map((element, index) => (
                        <div key={index}>
                            <details>
                                <summary>{element.question}</summary>
                                <p className="faq-content">{element.answer}</p>
                            </details>
                        </div>
                    ))
                }
            </div>
        </div>
    )
}

export default FrequentAskedQuestions
