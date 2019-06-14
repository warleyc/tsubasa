/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPlayableCardDetailComponent } from 'app/entities/m-playable-card/m-playable-card-detail.component';
import { MPlayableCard } from 'app/shared/model/m-playable-card.model';

describe('Component Tests', () => {
  describe('MPlayableCard Management Detail Component', () => {
    let comp: MPlayableCardDetailComponent;
    let fixture: ComponentFixture<MPlayableCardDetailComponent>;
    const route = ({ data: of({ mPlayableCard: new MPlayableCard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPlayableCardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPlayableCardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPlayableCardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPlayableCard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
