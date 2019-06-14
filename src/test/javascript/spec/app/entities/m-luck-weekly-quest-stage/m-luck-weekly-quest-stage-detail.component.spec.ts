/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLuckWeeklyQuestStageDetailComponent } from 'app/entities/m-luck-weekly-quest-stage/m-luck-weekly-quest-stage-detail.component';
import { MLuckWeeklyQuestStage } from 'app/shared/model/m-luck-weekly-quest-stage.model';

describe('Component Tests', () => {
  describe('MLuckWeeklyQuestStage Management Detail Component', () => {
    let comp: MLuckWeeklyQuestStageDetailComponent;
    let fixture: ComponentFixture<MLuckWeeklyQuestStageDetailComponent>;
    const route = ({ data: of({ mLuckWeeklyQuestStage: new MLuckWeeklyQuestStage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLuckWeeklyQuestStageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLuckWeeklyQuestStageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLuckWeeklyQuestStageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLuckWeeklyQuestStage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
