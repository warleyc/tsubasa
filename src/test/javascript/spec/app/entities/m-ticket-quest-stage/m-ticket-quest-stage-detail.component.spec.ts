/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTicketQuestStageDetailComponent } from 'app/entities/m-ticket-quest-stage/m-ticket-quest-stage-detail.component';
import { MTicketQuestStage } from 'app/shared/model/m-ticket-quest-stage.model';

describe('Component Tests', () => {
  describe('MTicketQuestStage Management Detail Component', () => {
    let comp: MTicketQuestStageDetailComponent;
    let fixture: ComponentFixture<MTicketQuestStageDetailComponent>;
    const route = ({ data: of({ mTicketQuestStage: new MTicketQuestStage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTicketQuestStageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTicketQuestStageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTicketQuestStageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTicketQuestStage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
