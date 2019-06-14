/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuerillaQuestWorldDetailComponent } from 'app/entities/m-guerilla-quest-world/m-guerilla-quest-world-detail.component';
import { MGuerillaQuestWorld } from 'app/shared/model/m-guerilla-quest-world.model';

describe('Component Tests', () => {
  describe('MGuerillaQuestWorld Management Detail Component', () => {
    let comp: MGuerillaQuestWorldDetailComponent;
    let fixture: ComponentFixture<MGuerillaQuestWorldDetailComponent>;
    const route = ({ data: of({ mGuerillaQuestWorld: new MGuerillaQuestWorld(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuerillaQuestWorldDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuerillaQuestWorldDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuerillaQuestWorldDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuerillaQuestWorld).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
