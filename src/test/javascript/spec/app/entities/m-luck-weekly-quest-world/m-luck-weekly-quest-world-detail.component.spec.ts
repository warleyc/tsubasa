/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLuckWeeklyQuestWorldDetailComponent } from 'app/entities/m-luck-weekly-quest-world/m-luck-weekly-quest-world-detail.component';
import { MLuckWeeklyQuestWorld } from 'app/shared/model/m-luck-weekly-quest-world.model';

describe('Component Tests', () => {
  describe('MLuckWeeklyQuestWorld Management Detail Component', () => {
    let comp: MLuckWeeklyQuestWorldDetailComponent;
    let fixture: ComponentFixture<MLuckWeeklyQuestWorldDetailComponent>;
    const route = ({ data: of({ mLuckWeeklyQuestWorld: new MLuckWeeklyQuestWorld(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLuckWeeklyQuestWorldDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLuckWeeklyQuestWorldDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLuckWeeklyQuestWorldDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLuckWeeklyQuestWorld).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
