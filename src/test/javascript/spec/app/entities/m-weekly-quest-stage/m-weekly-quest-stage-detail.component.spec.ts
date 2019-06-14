/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestStageDetailComponent } from 'app/entities/m-weekly-quest-stage/m-weekly-quest-stage-detail.component';
import { MWeeklyQuestStage } from 'app/shared/model/m-weekly-quest-stage.model';

describe('Component Tests', () => {
  describe('MWeeklyQuestStage Management Detail Component', () => {
    let comp: MWeeklyQuestStageDetailComponent;
    let fixture: ComponentFixture<MWeeklyQuestStageDetailComponent>;
    const route = ({ data: of({ mWeeklyQuestStage: new MWeeklyQuestStage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestStageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MWeeklyQuestStageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MWeeklyQuestStageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mWeeklyQuestStage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
