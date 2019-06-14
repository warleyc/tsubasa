/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackQuestWorldDetailComponent } from 'app/entities/m-timeattack-quest-world/m-timeattack-quest-world-detail.component';
import { MTimeattackQuestWorld } from 'app/shared/model/m-timeattack-quest-world.model';

describe('Component Tests', () => {
  describe('MTimeattackQuestWorld Management Detail Component', () => {
    let comp: MTimeattackQuestWorldDetailComponent;
    let fixture: ComponentFixture<MTimeattackQuestWorldDetailComponent>;
    const route = ({ data: of({ mTimeattackQuestWorld: new MTimeattackQuestWorld(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackQuestWorldDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTimeattackQuestWorldDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTimeattackQuestWorldDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTimeattackQuestWorld).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
