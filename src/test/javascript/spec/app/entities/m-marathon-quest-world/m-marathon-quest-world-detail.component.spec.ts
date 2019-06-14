/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonQuestWorldDetailComponent } from 'app/entities/m-marathon-quest-world/m-marathon-quest-world-detail.component';
import { MMarathonQuestWorld } from 'app/shared/model/m-marathon-quest-world.model';

describe('Component Tests', () => {
  describe('MMarathonQuestWorld Management Detail Component', () => {
    let comp: MMarathonQuestWorldDetailComponent;
    let fixture: ComponentFixture<MMarathonQuestWorldDetailComponent>;
    const route = ({ data: of({ mMarathonQuestWorld: new MMarathonQuestWorld(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonQuestWorldDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonQuestWorldDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonQuestWorldDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonQuestWorld).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
