/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestStageConditionDescriptionDetailComponent } from 'app/entities/m-quest-stage-condition-description/m-quest-stage-condition-description-detail.component';
import { MQuestStageConditionDescription } from 'app/shared/model/m-quest-stage-condition-description.model';

describe('Component Tests', () => {
  describe('MQuestStageConditionDescription Management Detail Component', () => {
    let comp: MQuestStageConditionDescriptionDetailComponent;
    let fixture: ComponentFixture<MQuestStageConditionDescriptionDetailComponent>;
    const route = ({ data: of({ mQuestStageConditionDescription: new MQuestStageConditionDescription(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestStageConditionDescriptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestStageConditionDescriptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestStageConditionDescriptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestStageConditionDescription).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
