/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestWorldDetailComponent } from 'app/entities/m-ticket-quest-world/m-ticket-quest-world-detail.component';
import { MTicketQuestWorld } from 'app/shared/model/m-ticket-quest-world.model';

describe('Component Tests', () => {
  describe('MTicketQuestWorld Management Detail Component', () => {
    let comp: MTicketQuestWorldDetailComponent;
    let fixture: ComponentFixture<MTicketQuestWorldDetailComponent>;
    const route = ({ data: of({ mTicketQuestWorld: new MTicketQuestWorld(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestWorldDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTicketQuestWorldDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTicketQuestWorldDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTicketQuestWorld).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
