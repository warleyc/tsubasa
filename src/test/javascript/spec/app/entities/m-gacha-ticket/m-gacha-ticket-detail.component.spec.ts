/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaTicketDetailComponent } from 'app/entities/m-gacha-ticket/m-gacha-ticket-detail.component';
import { MGachaTicket } from 'app/shared/model/m-gacha-ticket.model';

describe('Component Tests', () => {
  describe('MGachaTicket Management Detail Component', () => {
    let comp: MGachaTicketDetailComponent;
    let fixture: ComponentFixture<MGachaTicketDetailComponent>;
    const route = ({ data: of({ mGachaTicket: new MGachaTicket(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaTicketDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaTicketDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaTicketDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaTicket).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
