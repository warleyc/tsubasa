/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestWorldDetailComponent } from 'app/entities/m-weekly-quest-world/m-weekly-quest-world-detail.component';
import { MWeeklyQuestWorld } from 'app/shared/model/m-weekly-quest-world.model';

describe('Component Tests', () => {
  describe('MWeeklyQuestWorld Management Detail Component', () => {
    let comp: MWeeklyQuestWorldDetailComponent;
    let fixture: ComponentFixture<MWeeklyQuestWorldDetailComponent>;
    const route = ({ data: of({ mWeeklyQuestWorld: new MWeeklyQuestWorld(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestWorldDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MWeeklyQuestWorldDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MWeeklyQuestWorldDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mWeeklyQuestWorld).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
