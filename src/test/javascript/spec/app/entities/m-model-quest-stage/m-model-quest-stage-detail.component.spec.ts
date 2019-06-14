/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelQuestStageDetailComponent } from 'app/entities/m-model-quest-stage/m-model-quest-stage-detail.component';
import { MModelQuestStage } from 'app/shared/model/m-model-quest-stage.model';

describe('Component Tests', () => {
  describe('MModelQuestStage Management Detail Component', () => {
    let comp: MModelQuestStageDetailComponent;
    let fixture: ComponentFixture<MModelQuestStageDetailComponent>;
    const route = ({ data: of({ mModelQuestStage: new MModelQuestStage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelQuestStageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MModelQuestStageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MModelQuestStageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mModelQuestStage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
