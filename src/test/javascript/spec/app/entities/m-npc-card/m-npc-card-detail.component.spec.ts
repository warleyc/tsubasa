/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MNpcCardDetailComponent } from 'app/entities/m-npc-card/m-npc-card-detail.component';
import { MNpcCard } from 'app/shared/model/m-npc-card.model';

describe('Component Tests', () => {
  describe('MNpcCard Management Detail Component', () => {
    let comp: MNpcCardDetailComponent;
    let fixture: ComponentFixture<MNpcCardDetailComponent>;
    const route = ({ data: of({ mNpcCard: new MNpcCard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNpcCardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MNpcCardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MNpcCardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mNpcCard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
