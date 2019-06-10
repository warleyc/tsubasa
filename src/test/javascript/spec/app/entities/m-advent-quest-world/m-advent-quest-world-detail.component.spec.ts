/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAdventQuestWorldDetailComponent } from 'app/entities/m-advent-quest-world/m-advent-quest-world-detail.component';
import { MAdventQuestWorld } from 'app/shared/model/m-advent-quest-world.model';

describe('Component Tests', () => {
  describe('MAdventQuestWorld Management Detail Component', () => {
    let comp: MAdventQuestWorldDetailComponent;
    let fixture: ComponentFixture<MAdventQuestWorldDetailComponent>;
    const route = ({ data: of({ mAdventQuestWorld: new MAdventQuestWorld(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAdventQuestWorldDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MAdventQuestWorldDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAdventQuestWorldDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mAdventQuestWorld).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
