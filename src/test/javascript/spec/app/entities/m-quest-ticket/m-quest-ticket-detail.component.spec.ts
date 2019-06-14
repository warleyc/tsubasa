/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestTicketDetailComponent } from 'app/entities/m-quest-ticket/m-quest-ticket-detail.component';
import { MQuestTicket } from 'app/shared/model/m-quest-ticket.model';

describe('Component Tests', () => {
  describe('MQuestTicket Management Detail Component', () => {
    let comp: MQuestTicketDetailComponent;
    let fixture: ComponentFixture<MQuestTicketDetailComponent>;
    const route = ({ data: of({ mQuestTicket: new MQuestTicket(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestTicketDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestTicketDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestTicketDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestTicket).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
