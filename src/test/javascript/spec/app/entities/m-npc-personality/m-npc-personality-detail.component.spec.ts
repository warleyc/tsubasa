/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MNpcPersonalityDetailComponent } from 'app/entities/m-npc-personality/m-npc-personality-detail.component';
import { MNpcPersonality } from 'app/shared/model/m-npc-personality.model';

describe('Component Tests', () => {
  describe('MNpcPersonality Management Detail Component', () => {
    let comp: MNpcPersonalityDetailComponent;
    let fixture: ComponentFixture<MNpcPersonalityDetailComponent>;
    const route = ({ data: of({ mNpcPersonality: new MNpcPersonality(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNpcPersonalityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MNpcPersonalityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MNpcPersonalityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mNpcPersonality).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
