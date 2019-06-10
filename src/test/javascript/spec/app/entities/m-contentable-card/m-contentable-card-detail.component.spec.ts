/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MContentableCardDetailComponent } from 'app/entities/m-contentable-card/m-contentable-card-detail.component';
import { MContentableCard } from 'app/shared/model/m-contentable-card.model';

describe('Component Tests', () => {
  describe('MContentableCard Management Detail Component', () => {
    let comp: MContentableCardDetailComponent;
    let fixture: ComponentFixture<MContentableCardDetailComponent>;
    const route = ({ data: of({ mContentableCard: new MContentableCard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MContentableCardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MContentableCardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MContentableCardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mContentableCard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
