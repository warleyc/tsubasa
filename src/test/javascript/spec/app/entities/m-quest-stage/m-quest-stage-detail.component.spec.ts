/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestStageDetailComponent } from 'app/entities/m-quest-stage/m-quest-stage-detail.component';
import { MQuestStage } from 'app/shared/model/m-quest-stage.model';

describe('Component Tests', () => {
  describe('MQuestStage Management Detail Component', () => {
    let comp: MQuestStageDetailComponent;
    let fixture: ComponentFixture<MQuestStageDetailComponent>;
    const route = ({ data: of({ mQuestStage: new MQuestStage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestStageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestStageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestStageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestStage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
